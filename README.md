# Zappos-App-Challenge
This is a coding chellenge from Zappos Family. 

Build an app that compares prices between Zappos.com and 6pm.com (Zappos’ sister site). The app should take input from the user as a search query and load the results from the Zappos endpoint. The products should be displayed in a RecyclerView. When a user taps on a product in the RecyclerView, the 6pm endpoint should be queried to find a match for the Zappos product. If the product exists and is cheaper on 6pm, notify the user. 

Bonus points for: 
Material Design 
Well handled configuration changes 
Data Binding 

The following REST requests can be used to get back search results: 
https://api.zappos.com/Search?term=%3CinsertSearchTermHere%3E&key=b743e26728e16b81da139182bb2094357c31d331 
  
https://api.6pm.com/Search?term=%3CinsertSearchTermHere%3E&key=524f01b7e2906210f7bb61dcbe1bfea26eb722eb 
  
Example: 
https://api.zappos.com/Search?term=nike&key=b743e26728e16b81da139182bb2094357c31d331 

If you completed the above challenge with ease, try this: 
“Share” a product to nearby friends who have your app running on their phone such that they are able to view the same product (when clicking on the shared link or some other awesome magical way) 
Provide the release build .apk file along with a public git repository url for viewing the source code. Title your project ILoveNougat :) 

