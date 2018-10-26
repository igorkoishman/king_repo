**posts**
----

to get post
 **Method:**
 
 **HEADERS:**
 CONTENT_TYPE application/json
 ACCEPT application/json
 
`GET`
* **URL**
http://host:port/post/{id}

**Required:**
   `id=[integer]`

to add new  post
 **Method:**
`POST`
**HEADERS:**
 CONTENT_TYPE application/json
 ACCEPT application/json
* **URL**
http://host:port/post

 **BODY:**
{"message":"mehavia"}


to add new  post
 **Method:**
`PUT`
**HEADERS:**
 CONTENT_TYPE application/json
 ACCEPT application/json
* **URL**
http://host:port/post

 **BODY:**
{"postId":{id<long>}:
  "message":"mehavia"}
  
  
  to get post
 **Method:**
 
 **HEADERS:**
 CONTENT_TYPE application/json
 ACCEPT application/json
 
 to get top posts 
`GET`
* **URL**
http://host:port/post/top


to voting
 **Method:**
`POST`
**HEADERS:**
 CONTENT_TYPE application/json
 ACCEPT application/json
* **URL**
http://host:port/vote

 **BODY:**
{"voterId":{voteId}<long>,  "postId":{postId}<long>, "vote":{your vote}<1/-1>}
  
  
  RESPONSE ERROR OPTIONALS 200/201 SUCCESS
  404 NOT FOUND
  400 BAD REQUEST
  500 INTERNAL 






