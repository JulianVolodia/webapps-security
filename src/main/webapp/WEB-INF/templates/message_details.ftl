<#import "layout/layout_logged.ftl" as layout>

<@layout.page>


    <br><br>
    <div class="message_details">
        <strong>Date:</strong> ${message.insertTs} <br>
        <strong>From:</strong> ${message.senderFullName} <br>
        <strong>To:</strong> ${message.recipientFullName} <br>
        <strong>Subject:</strong> ${message.subject} <br>
        <strong>Content:</strong><br>
        ${message.content}
    </div>


    <div class="new_message">
        <p><strong>Reply:</strong></p>
        <form action="/webapps_security/messages" method="POST" accept-charset="UTF-8">
            <input type="text" name="message_subject" value="Re: ${message.subject}"> <br>
            <textarea name="message_content" cols="70" rows="5"></textarea>
            <input type="hidden" name="recipient_id" value="<#if user.id==message.senderId>${message.recipientId}<#else>${message.senderId}</#if>">
            <br/>
            <input type="submit" value="Send" />
        </form>
    </div>


</@layout.page>
