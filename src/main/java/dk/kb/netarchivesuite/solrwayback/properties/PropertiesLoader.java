package dk.kb.netarchivesuite.solrwayback.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.lang3.StringUtils;

public class PropertiesLoader {

    private static final Logger log = LoggerFactory.getLogger(PropertiesLoader.class);
    private static final String PROPERTY_FILE = "solrwayback.properties";

    private static final String SOLR_SERVER_PROPERTY="solr.server";
    private static final String PROXY_PORT_PROPERTY="proxy.port";
    private static final String PROXY_ALLOW_HOSTS_PROPERTY="proxy.allow.hosts";
    private static final String WARC_FILE_RESOLVER_CLASS_PROPERTY="warc.file.resolver.class";
    private static final String WAYBACK_BASEURL_PROPERTY="wayback.baseurl";
    private static final String CHROME_COMMAND_PROPERTY="chrome.command";
    private static final String SCREENSHOT_TEMP_IMAGEDIR_PROPERTY="screenshot.temp.imagedir";

    private static final String FACETS_PROPERTY = "facets";

    private static Properties serviceProperties = null;

    public static String SOLR_SERVER = null;
    public static String WAYBACK_BASEURL = null;
    public static String CHROME_COMMAND= null;
    public static String SCREENSHOT_TEMP_IMAGEDIR = null;
    public static String PROXY_PORT= null;
    public static String PROXY_ALLOW_HOSTS= null;
    public static String WARC_FILE_RESOLVER_CLASS = null;
    public static List<String> FACETS =
            Arrays.asList("domain", "content_type_norm", "type", "crawl_year", "status_code", "public_suffix");


    public static void initProperties() {
        try {

            log.info("Initializing solrwayback-properties");

            String user_home=System.getProperty("user.home");
            log.info("Load properties: Using user.home folder:" + user_home);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(user_home,PROPERTY_FILE)), "ISO-8859-1");

            serviceProperties = new Properties();
            serviceProperties.load(isr);
            isr.close();

            SOLR_SERVER =serviceProperties.getProperty(SOLR_SERVER_PROPERTY);
            WAYBACK_BASEURL = serviceProperties.getProperty(WAYBACK_BASEURL_PROPERTY);
            CHROME_COMMAND = serviceProperties.getProperty(CHROME_COMMAND_PROPERTY);
            SCREENSHOT_TEMP_IMAGEDIR = serviceProperties.getProperty(SCREENSHOT_TEMP_IMAGEDIR_PROPERTY);
            PROXY_PORT = serviceProperties.getProperty(PROXY_PORT_PROPERTY);
            PROXY_ALLOW_HOSTS = serviceProperties.getProperty(PROXY_ALLOW_HOSTS_PROPERTY);
            WARC_FILE_RESOLVER_CLASS = serviceProperties.getProperty(WARC_FILE_RESOLVER_CLASS_PROPERTY);
            FACETS = Arrays.asList(getProperty(FACETS_PROPERTY, StringUtils.join(FACETS, ",")).split(", *"));

            log.info("Property:"+ SOLR_SERVER_PROPERTY +" = " + SOLR_SERVER);
            log.info("Property:"+ WAYBACK_BASEURL_PROPERTY +" = " + WAYBACK_BASEURL);
            log.info("Property:"+ PROXY_PORT_PROPERTY +" = " + PROXY_PORT);
            log.info("Property:"+ PROXY_ALLOW_HOSTS_PROPERTY +" = " + PROXY_ALLOW_HOSTS);
            log.info("Property:"+ CHROME_COMMAND_PROPERTY +" = " + CHROME_COMMAND);
            log.info("Property:"+ SCREENSHOT_TEMP_IMAGEDIR_PROPERTY +" = " + SCREENSHOT_TEMP_IMAGEDIR);
            log.info("Property:"+ WARC_FILE_RESOLVER_CLASS_PROPERTY +" = " + WARC_FILE_RESOLVER_CLASS);
            log.info("Property:"+ FACETS_PROPERTY +" = " + FACETS);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("Could not load property file:"+ PROPERTY_FILE);
        }
    }

    public static String getProperty(String key, String defaultValue) {
   	    if (serviceProperties == null) {
   	        initProperties();
           }
           Object o = serviceProperties.getProperty(key);
   	    return o == null ? defaultValue : o.toString();
       }
}
