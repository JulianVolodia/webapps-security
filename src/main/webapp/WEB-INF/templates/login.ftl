<#import "layout/layout_not_logged.ftl" as layout>

<@layout.page>

    <h2>Log in</h2>

    <#if authFailed??>
        <p>AUTHENTICATION FAILED</p>
    </#if>

    <form action="login" method="POST">
        <input type="text" name="login" />
        <br/>
        <input type="password" name="passwd" />
        <br/>
        <input type="submit" value="Log in" />
    </form>


</@layout.page>
