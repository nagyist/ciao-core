Overview
========

This is the core framework for ciao, linking in with camel and adding functionality for CIPs. It links the configuration functionality in
[ciao-configuration](https://github.com/nhs-ciao/ciao-utils/tree/master/ciao-configuration) 
into camel so that standard camel property placeholders can be used in camel routes - for example:

	.log("***** Config value = {{ConfigValue}} *****")


Usage
-----

To use this in your CIP code, you will need to subclass two classes:

**CIPRoutes**

Override the configure() method - this is where you will define your camel routes. For example:

```java
@Override
public void configure() {
	from("jetty:http://0.0.0.0:8080/test?traceEnabled=true").routeId("testRouteHTTP")
		.log("***** Config value = {{ConfigValue}} *****")
		.to("bean:loggingBean?method=logValue({{ConfigValue}})");
	super.configure();
}
```

Note: you should always call super.configure at the end of your method to ensure any common routes for all CIPs are also initialised.

**RunCIP**

Override the populateCamelRegistry() to configure your custom beans and other custom components - for example:

```java
@Override
protected void populateCamelRegistry(JndiContext jndi)
		throws NamingException {
	jndi.bind("loggingBean", new LoggingBean());
}
```

Call super.init to initialise the camel context, and then call super.startCamel to start your CIP - for example:

```java
CamelContext context = super.init(args, "testCIP.properties");
context.addRoutes(new Route());
super.startCamel(context);
```

You can see a working example of the above in the unit test code included in this project.