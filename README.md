# SolrWayback

Important:
This is latest version to work with warc-indexer up to version 2.0.2 and it will not be maintained. 
For warc-indexer 3.0 indexes use the master branch. 


SolrWayback is web-application for browsing historical harvested ARC/WARC files similar
to the Internet Archive Wayback Machine. The SolrWayback depends on a Solr server where
Arc/Warc files have been indexed using the British Library WARC-Indexer. The Netsearch application is just a simple book keeping application on top of the WARC-Indexer that also
controls the indexing. Unlike the Wayback Machine the SolrWayback does not need the
CDX-server with meta data for the harvest. It only uses the Solr server and the raw
Arc/Warc files.

 Warc-indexer: https://github.com/ukwa/webarchive-discovery/tree/master/warc-indexer<br>
 Netsearch(Archon/Arctika): https://github.com/netarchivesuite/netsearch<br>


<p align="center"> 
   <img src="https://github.com/netarchivesuite/solrwayback/blob/master/doc/solrwayback_search.png?raw=true" width="600" height="400"/>
</p>
<p align="center">
  Search example showing hits. Images are shown in search-result.
</p>

<p align="center"> 
   <img src="https://github.com/netarchivesuite/solrwayback/blob/master/doc/image_search.png?raw=true" width="600" height="400"/>
</p>
<p align="center">
  Google like image search in the web-archive
</p>

<p align="center"> 
   <img src="https://github.com/netarchivesuite/solrwayback/blob/master/doc/solrwayback_demo.png?raw=true" width="600" height="400"/>
</p>
<p align="center">
Solrwayback showing an archived webpage with an overlay statistics and further navigation options.
</p>


<p align="center"> 
   <img src="https://github.com/netarchivesuite/solrwayback/blob/master/doc/multiple_pagepreviews.png?raw=true" width="600" height="400"/>
</p>
<p align="center">
Page preview for different harvest times of a given url. Images are generated real-time.
</p>



SolrWayback comes with additional features:
* Image search similar to google images
* Link graph showing links (ingoing/outgoing) for domains using the D3 javascript framework.
* Raw download of any harvested resource from the binary Arc/Warc file.


<p align="center"> 
   <img src="https://github.com/netarchivesuite/solrwayback/blob/master/doc/solrwayback_linkgraph.png?raw=true" width="600" height="400"/>
</p>
<p align="center">
Interactive domain link graph
</p>

<p align="center"> 
   <img src="https://github.com/netarchivesuite/solrwayback/blob/master/doc/solrwayback_crawltimes.png?raw=true" />
</p>
<p align="center">
Github like visualization of crawltimes
</p>



For image search see the documentation <a href="https://github.com/netarchivesuite/solrwayback/blob/master/doc/imagesearch.txt" >Image search documentation </a>


The API for linking to and browsing archived webpages is the same as for Internet Archive:<br>

Internet Archive:https://web.archive.org/web/20080213093319/http://www.statsbiblioteket.dk/ <br>
SolrWayback: http://server/solrwayback/services/wayback?waybackdata=20140515140841/http://statsbiblioteket.dk/ <br>

If using a Solr search based web archive such as Shine (https://github.com/netarchivesuite/shine) or Blacklight (https://github.com/projectblacklight/blacklight)
you only need to change to property pointing from the wayback server to the SolrWayback server.

The SolrWayback web application comes with a simple front-end for testing Solr-search and image search.
 
 
 
## Requirements
 * JDK 1.7+
 * Maven 3 
 * Some Arc/Warc files 
 * Tomcat 7+  or another J2EE server for deploying the WAR-file
 * A Solr server with the index build from the Arc/Warc files using the Warc-Indexer.
 * The J2EE server must have the Arc/Warc file drive mounted
 * (Optional) phanomjs native installed to generate webpage previews.
 
## Build and usage
 * Build the application with: mvn package
 * Deploy the solrwayback.war file in a web-container. 
 * Copy resources/properties/solrwayback.properties to user/home/ folder for the J2EE server
 * Modify the 2 properties in solrwayback.properties
 * Optional: configure the log4j using the files in resources/tomcat
 * Search url: localhost:8080/solrwayback

## Run using Docker
 * Copy resources/properties/solrwayback.properties to the project root directory and modify it to your needs.
 * Make sure Docker Engine is installed.
 * Run ./docker-run.sh from the project root.

## Contact
* Thomas Egense (thomas.egense@gmail.com) 
* Niels Gamborg (nig@kb.dk) 

Feel free to send emails with comments and questions.

## Warc-indexer/Solr 
All entries from the arc/warc files are indexed as separate documents using the WARC-Indexer and using the lucene schema required by the WARCIndexer.
A document is Solr can be html, image, video, audio, js etc. (content_type_norm)
All document contains a field with arcfilename and offset, this is so  the binary from
the arc/warc file can be loaded again - not through solr but by IO read on the disk where the 
arc/warc file is stored.
 



      

