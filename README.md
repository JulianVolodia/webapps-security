webapps-security
================

This is an example JEE project with a lot of serious security issues.

It allows you to have fun with the following vulnerabilities:
- SQL Injection
- HTML Injection
- Cross-Site Scripting (XSS)
- Cross-Site Request Forgery (CSRF)
- Session Fixation
- Insecure Direct Object References
- ... and perhaps even some more :)

It's by no means optimized, well-documented or coded with well-crafted-code-princilpes. It was made just to present how easy it is to break into the unprotected IT system.

**DO NOT USE THIS SOFTWARE ON PRODUCTION - IT'S VERY, VERY UNSAFE!**

It's licensed on the terms of the Apache License 2.0. Do whatever you like with this code.

## How to build it?

You'll need (at least) JDK and Maven. Then just type:

    mvn package

and deploy it to the JBoss. Tested with JBoss 7.1.1 and PostgreSQL 9.1.
