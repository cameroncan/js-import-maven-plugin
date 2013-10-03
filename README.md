js-import-maven-plugin
======================
A maven plugin that provides a "maveny" way to handle js files. The plugin enables the use of maven artifacts of type "js" which will subsequently be resolved to the correct version (if there are conflicts) and downloaded to a specified location. 

Origins
-------
This is a fork of the project at http://mojo.codehaus.org/js-import-maven-plugin/index.html that contains the patches were submitted in issue #8, #9, and #10 as well as some additional changes. The great majority of credit for this plugin belongs to Christopher Hunt, the original creator and maintainer of the project. I've just added my two cents.

This fork was created due to the apparent stale (or slow) state of the original plugin. Creating this repo will also allow for pull requests and forking, which was not possible there. Additionally, and perhaps more important, is that I have deviated from the original creator's mentality in regard to minification. On their [project site](http://mojo.codehaus.org/js-import-maven-plugin/usage.html) it reads:

>"We recommend uploading non-minified versions of JS files into a repository. This makes debugging issues with an application significantly easier. Minification is something that can be applied to both the application's artifacts and its depedencies as a separate concern at a later stage in the development cycle."

However, in our case we are using this plugin solely for the purpose of managing and resolving our 3rd party js. For 3rd party libraries I disagree with the above statement:

1. Why recompile/re-minify JS that the developer never makes changes to.
2. Many creators of js libraries now provide their specific minified version and are taking responsibility of the artifact
3. In some cases, (such as angularJS) trying to minify their source will not work without special configuration, and tell users not to do this.

In my changes I may have overlooks some important features for JS that the user owns, and if so, let me know. However, the greatest use case seems to be for 3rd party js.

Documentation
-------------
All of the [documentation of the original plugin](http://mojo.codehaus.org/js-import-maven-plugin/index.html) is still valid, however there are some additional features that I've added and they are described below:

1. Add the capability of the plugin to handle a "min" classifier on the dependency. Example:

    	<dependency>
      		<groupId>com.jquery</groupId>
      		<artifactId>jquery</artifactId>
       		<version>1.8.2</version>
       		<type>js</type>
        	<classifier>min</classifier>
    	</dependency>


This will allow you to choose to download the minified version of the library or the non-minified version (e.g. for the dev environment). I have also add the ability to the plugin to do conflict resolution regarding the minified vs. the non-minified versions of the file. They are mutually exclusive and the plugin will resolve any conflicting dependencies. The rules for the resolution are as follows:
  1. If both are specified in the project's pom file (non-transitive dependencies) then both are downloaded.
  2. If one is a transitive dependency, and the other a direct dependency, then the direct dependency is downloaded
  3. If both are transitive dependencies, then the non-minified downloaded (preferred, as minification can be done to it later if needed).

2. Added a property `forceJSReload`. This will force a reload of the dependencies into your `${targetFolder}`. This is useful within ide's such as eclipse where a full clean is not performed. When set to true all js dependencies will be reimported into your `${targetFolder}` whereas false(default) will reload only if there have been updates to the file (after the initial load). This is optional and the default value is false.

