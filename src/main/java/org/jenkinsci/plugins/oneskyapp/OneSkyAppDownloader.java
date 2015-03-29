package org.jenkinsci.plugins.oneskyapp;

import hudson.EnvVars;
import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.remoting.Callable;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import net.sf.json.JSONObject;
import org.jenkinsci.plugins.oneskyapp.model.OneSkyAppFile;
import org.jenkinsci.plugins.oneskyapp.model.OneSkyAppProject;
import org.jenkinsci.plugins.oneskyapp.model.OneSkyAppProjectLanguage;
import org.jenkinsci.remoting.RoleChecker;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import java.io.File;
import java.util.List;

public class OneSkyAppDownloader extends Builder {

    private String apiKey;
    private String secretKey;
    private int projectId;
    private String destinationFilePattern;
    private boolean onlyIfReadyToPublish;


    @DataBoundConstructor
    public OneSkyAppDownloader(String apiKey, String secretKey, int projectId, String destinationFilePattern, boolean onlyIfReadyToPublish) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.projectId = projectId;
        this.destinationFilePattern = destinationFilePattern;
        this.onlyIfReadyToPublish = onlyIfReadyToPublish;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getDestinationFilePattern() {
        return destinationFilePattern;
    }

    public void setDestinationFilePattern(String destinationFilePattern) {
        this.destinationFilePattern = destinationFilePattern;
    }

    public boolean isOnlyIfReadyToPublish() {
        return onlyIfReadyToPublish;
    }

    public void setOnlyIfReadyToPublish(boolean onlyIfReadyToPublish) {
        this.onlyIfReadyToPublish = onlyIfReadyToPublish;
    }

    @Override
    public boolean perform(final AbstractBuild build, Launcher launcher, final BuildListener listener) {
        try {
            build.getWorkspace().getChannel().call(new Callable<Object, Throwable>() {
                @Override
                public Object call() throws Throwable {
                    OneSkyAppClient oneSkyAppClient = new OneSkyAppClient(apiKey, secretKey);
                    final OneSkyAppProject project = new OneSkyAppProject();
                    project.setId(projectId);
                    final List<OneSkyAppProjectLanguage> languages = oneSkyAppClient.getProjectLanguages(project);
                    if (languages != null) {

                        final List<OneSkyAppFile> files = oneSkyAppClient.getFiles(project);
                        if (files != null) {
                            for (OneSkyAppFile file : files) {
                                for (OneSkyAppProjectLanguage language : languages) {

                                    if (language.isReadyToPublish() || !isOnlyIfReadyToPublish()) {
                                        final FilePath workspace = build.getWorkspace();

                                        final EnvVars environment = build.getEnvironment(listener);
                                        environment.put("OSA_FILENAME", file.getFileName());
                                        if (language.isBaseLanguage()) {
                                            environment.put("OSA_SEP", "");
                                            environment.put("OSA_LOCALE", "");
                                        } else {
                                            environment.put("OSA_SEP", "-");
                                            environment.put("OSA_LOCALE", language.getLocale());
                                        }
                                        final String filename = environment.expand(destinationFilePattern);
                                        final File outputFile = new File(workspace.getRemote() + "/" + filename);

                                        oneSkyAppClient.getTranslation(project, file, language, outputFile);
                                    }
                                }
                            }
                        }
                    }
                    return null;
                }

                @Override
                public void checkRoles(RoleChecker roleChecker) throws SecurityException {

                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return true;
    }

    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl) super.getDescriptor();
    }

    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        public DescriptorImpl() {
            load();
        }

        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        public String getDisplayName() {
            return "OneSkyApp translations download";
        }



        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
            req.bindJSON(this, formData);
            save();
            return super.configure(req, formData);
        }


    }
}

