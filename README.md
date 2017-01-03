Configuration (config)
----------------------
Uses Java DSLs

1. Jayway JsonPath as a Java port of [Stefan Goessner JsonPath implementation](http://goessner.net/articles/JsonPath/).
2. Java's own XPath
3. simple Property grabber code
4. (To be added) YAML-path

to simply stream through configuration files a get properties as expressed by their paths. Currently, no memory loading is supported. So,
in case the requirement is to only mine a few information (2/3) from configuration files streaming
is a good idea.

  For syntax on Json-Path and XPath, see documents in [syntax-doc](./syntax-doc)

  My plans for the future will be to

  1. Improved type casting.
  2. Binding variables to configurations with the use of annotations.
  2. create a very simple common quick compiling DSL to compile an expression and
  retrieve path values for xml, json, yaml and properties. A complex algorithm
   to stream through files and simply look for required items.
  3. remove dependency on external libraries.
