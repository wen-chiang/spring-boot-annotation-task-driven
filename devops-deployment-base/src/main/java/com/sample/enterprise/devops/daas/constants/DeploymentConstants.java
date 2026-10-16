package com.sample.enterprise.devops.daas.constants;

public class DeploymentConstants {


    public static final String LOGIC_STATUS = "LOGIC_STATUS";
    public static final String ROUTINE_STATUS = "ROUTINE_STATUS";

    public static final String SECRET_ENDPOINT = "/secret";
    public static final String EXTERNAL_ENDPOINT = "/external";

    public static final String OPENSHIFT_DEPLOY_PREPARE_ENDPOINT = "/openshift/deploy/prepare";
    public static final String OPENSHIFT_DEPLOY_PREPARE_EXPORT_ENDPOINT = "/openshift/deploy/prepare/export";
    public static final String OPENSHIFT_DEPLOY_PREPARE_IMPORT_ENDPOINT = "/openshift/deploy/prepare/import";
    public static final String OPENSHIFT_ROLLOUT_ENDPOINT = "/openshift/deploy/rollout";
	public static final String OPENSHIFT_ROLLBACK_ENDPOINT = "/openshift/deploy/rollback";
    public static final String OPENSHIFT_STATUS_ENDPOINT = "/openshift/deploy/status";
    public static final String OPENSHIFT_DEACTIVATE_ENDPOINT = "/openshift/deploy/deactivate";
    public static final String OPENSHIFT_PROMOTION_ENDPOINT = "/openshift/promote";
    public static final String OPENSHIFT_CLEANUP_ENDPOINT = "/openshift/cleanup";
    public static final String OPENSHIFT_CONFIG_ENDPOINT = "/openshift/config";
    public static final String OPENSHIFT_BUILD_IMAGE_ENDPOINT = "/openshift/deploy/buildImage";
    public static final String OPENSHIFT_CLIENT_VALIDATE_ENDPOINT = "/openshift/secret/validateUser";
    public static final String OPENSHIFT_CLIENT_USER_VALIDATE_ENDPOINT = "/openshift/validateUser";
    public static final String OPENSHIFT_CLIENT_SECRET_ENDPOINT = "/openshift/secret/inject";
    public static final String OPENSHIFT_CLIENT_CONFIG_ENDPOINT = "/openshift/secret/validateConfig";
    public static final String OPENSHIFT_DRYRUN_ENDPOINT = "/openshift/deploy/dryrun";

    public static final String OPENSHIFT_CHANGE_RECORD_VALIDATE_ENDPOINT = "/openshift/deploy/validateChangeRecord";
    public static final String OPENSHIFT_REBUILD_IMAGE_ENDPOINT = "/openshift/rebuildImage";
    public static final String ENTERPRISE_PRODCHECK_ENDPOINT = "/enterprise/prodCheck";

    public static final String CHEF_DEPLOYMENT_ENDPOINT = "/chef/deploy";
    public static final String CHEF_PROMOTION_ENDPOINT = "/chef/promote";
    public static final String CHEF_STATUS_ENDPOINT = "/chef/status";
    public static final String CHEF_CLEANUP_ENDPOINT = "/chef/cleanup";

    public static final String ANSIBLE_DEPLOYMENT_ENDPOINT = "/ansible/deploy";
    public static final String ANSIBLE_PROMOTION_ENDPOINT = "/ansible/promote";
    public static final String ANSIBLE_STATUS_ENDPOINT = "/ansible/status";
    public static final String ANSIBLE_CLEANUP_ENDPOINT = "/ansible/cleanup";
    public static final String ANSIBLE_GET_LOGS_ENDPOINT = "/ansible/getLogs";
    public static final String ANSIBLE_ACTIVATE_PROFILE_ENDPOINT = "/ansible/activate";

    public static final String OPENSHIFT_MANAGE_SECRET_ENDPOINT = "/openshift/manageSecret";
    public static final String OPENSHIFT_SECRET_KEY_ENDPOINT = "/openshift/secretKey";
    public static final String OPENSHIFT_MANAGE_SECRET_WITH_UPLOAD_ENDPOINT = "/openshift/manageSecretWithUpload";
    public static final String SMARTTS_ENDPOINT = "/postdeploy/smartts";

    public static final String DEPLOYMENT_KEY = "DEPLOY";
    public static final String PREPARE_KEY = "PREPARE";
    public static final String EXPORT_PREPARE_KEY = "EXPORT-PREPARE";
    public static final String IMPORT_PREPARE_KEY = "IMPORT-PREPARE";
    public static final String ROLLOUT_KEY = "ROLLOUT";
	public static final String ROLLBACK_KEY = "ROLLBACK";
    public static final String GET_LOGS_KEY = "GET-LOGS";
    public static final String PROMOTION_KEY = "PROMOTE";
    public static final String STATUS_KEY = "STATUS";
    public static final String CLEANUP_KEY = "CLEANUP";
    public static final String ACTIVATE_PROFILE_KEY = "ACTIVATE";
    public static final String CONFIG_KEY = "CONFIG";
    public static final String BUILD_IMAGE_KEY = "BUILD_IMAGE";
    public static final String MANAGE_SECRET_KEY = "MANAGE-SECRET";
    public static final String PUBLISHER_KEY = "PUBLISHER";
    public static final String SECRET_INJECT_KEY = "SECRET-INJECT";
    public static final String SECRET_VALIDATE_USER_KEY = "SECRET-VALIDATE-USER";
    public static final String SECRET_VALIDATE_CONFIG_KEY = "SECRET-VALIDATE-CONFIG";
    public static final String DRYRUN_KEY = "DRYRUN";
    public static final String DEACTIVATE_KEY = "DEACTIVATE";
    public static final String PROD_CHECK_KEY = "PROD-CHECK";
    public static final String VALIDATE_CHANGE_RECORD_KEY = "VALIDATE-CHANGE-RECORD";
    public static final String REBUILD_IMAGE_KEY = "REBUILD_IMAGE";


    public static final String FAIL_STATUS_KEY = "FAIL";
    public static final String SUCCESS_STATUS_KEY = "SUCCESS";
    public static final String NOT_FOUND_STATUS_KEY = "NOT_FOUND";
    public static final String SUBMITTED_STATUS_KEY = "SUBMITTED";
    public static final String PROGRESS_STATUS_KEY = "PROGRESS";

    public static final String PROD_PROMOTE_KEY = "prod";

    public static final String PROFILE_BASE_ROUTE_URI = "PROFILE_BASE_ROUTE_URI";
    public static final String BRANCH_BASE_ROUTE_URI = "BRANCH_BASE_ROUTE_URI";
    public static final String CHOOSE_ROUTE_URI = "CHOOSE_ROUTE_URI";

    public static String SYSTEM_DEFINE_KEY = "SYSTEM_DEFINE";
    public static String SYSTEM_DEFINE_TAR_KEY = "SYSTEM_DEFINE_TAR";
    public static final String SESSION_TAR_URL_KEY = "SESSION_TAR_URL";

    public static final String ARTIFACTORY_HOST = "artifactory.sample.com";
    public static final String ARTIFACTORY_URL = "https://" + ARTIFACTORY_HOST + ":443/artifactory";
    public static final String ARTIFACTORY_JENKINS_RELEASE_SHARED_REPO = ARTIFACTORY_URL + "/jenkins-release-shared";
    public static final String ARTIFACTORY_JENKINS_RELEASE_REPO = ARTIFACTORY_URL + "/jenkins-release";

    public static String ZONE_INFO_KEY = "ZONE_INFO_KEY";

    public static String EDP_CONFIG_FILE = "edp.config.yml";
}
