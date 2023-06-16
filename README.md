<b>Reactive Java Application</b> to interact with the reddit API and do operations on the data fetched.
Made using <b>Spring</b> framework build using <b>Gradle</b> and <b>MongoDB</b> database.
Used <b>Jackson</b> library to map the JSON data to Java objects for storing in the database.
Used <b>Spring Webflux</b> to make the applicaiton reactive and non-blocking to improve the performance when the load increases.

<b>API’s used-</b>
1. https://www.reddit.com/api/v1/access_token
     <br> To get the auth token for using the Reddit API.
2. https://oauth.reddit.com
      <br>Base URL for calling the Reddit API.
<br>

<b>Endpoints used-</b>
1. /r/subreddit/new
       <br>For fetching the posts from a subreddit.
2. /api/submit
       <br>For posting a post on reddit.
<br>

<b>Endpoints created-</b>
1. /user/test/{subreddit_name}
      <br>For fetching the posts of a particular subreddit and storing it in the database.
2. /user/get/{user_name}
      <br>For getting the posts of a particular user from the database.
3. /user/delete/{user_name}
      <br>For deleting the posts of a particular user from the database.
4. /user/keyword/{word}
      <br>For getting the posts whose body contains a particular keyword from the database.
5. /user/sort
      <br>To get the posts from the database sorted by date of post.
6. /reddit/post
      <br>To post on a subreddit on reddit and also storing the latest posts of that subreddit on the database.
