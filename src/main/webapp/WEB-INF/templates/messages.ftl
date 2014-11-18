<#import "layout/layout_logged.ftl" as layout>

<@layout.page>


    <#if message_sent??>
        <p>Message has been sent successfully</p>
    </#if>


    <div id="received_messages" class="messages">
        <p><strong>Received messages: </strong></p>
    <#list received_messages as received_message>
        <p><a href="/webapps_security/message_details?message_id=${received_message.id?c}">
            ${received_message.insertTs} - ${received_message.subject} - ${received_message.senderFullName}
        </a></p>
    </#list>
    </div>

    <br>
    <div id="sent_messages" class="messages">
        <p><strong>Sent messages: </strong></p>
    <#list sent_messages as sent_message>
        <p><a href="/webapps_security/message_details?message_id=${sent_message.id?c}">
            ${sent_message.insertTs} - ${sent_message.subject} - ${sent_message.recipientFullName}
        </a></p>
    </#list>
    </div>


</@layout.page>
