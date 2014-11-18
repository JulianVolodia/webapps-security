<#import "layout/layout_logged.ftl" as layout>

<@layout.page>

        <form action="" method="POST" accept-charset="UTF-8">
        <div class="new_post">
                <div class="new_post_content">
                    <textarea name="post_content" cols="100" rows="6"></textarea>
                </div>


                <div class="buttons">
                    <input type="submit" value="Publish" />
                </div>

            <div style="height: 0px; clear: both;"></div>
        </div>
        </form>

    <div class="posts">
    <#list wall_posts as post>

            <div class="post">
                <span class="post_author">${post.postUserFullName}</span>
                <span class="post_date">(${post.postInsertTs})</span>
                <div class="post_content">${post.postContent}</div>
            </div>
    </#list>
    </div>


</@layout.page>

