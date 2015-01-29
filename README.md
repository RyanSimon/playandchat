# Play and Chat
Prototype Android app that allows users to play a game and chat with friends simultaneously.

Lollipop version             |  KitKat version
:-------------------------:|:-------------------------:
![](https://raw.githubusercontent.com/ryan-simon/playandchat/master/screenshots/PlayandChatLollipop.png)  |  ![](https://raw.githubusercontent.com/ryan-simon/playandchat/master/screenshots/PlayandChatKitKat.png)

# Design
Designed around the user's profile. The profile has prominent placement, and lists the games the user has played for quick access.

# Libraries and Techniques Used
Android support libraries, and a little custom code went into the development of the prototype.

#### RecyclerView [[link]](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.html)
Those familiar with the ViewHolder pattern and its use with ListViews can rejoice in the beautiful flexibility that is RecyclerView. Play and Chat's Profile screen uses this to keep scrolling between items smooth, and lag free.

RecyclerView incorporates its own version of the ViewHolder pattern to maintain the performance gains, yet allows for much more flexibility.

For example, this flexibility is especially useful in handling click events on different Views within an individual row.

#### Toolbar [[link]](http://developer.android.com/reference/android/widget/Toolbar.html)
As Android's replacement for the ActionBar, the Toolbar's greatest strength is its ability to be injected straight into a layout. This allows for an incredible amount of design flexibility.

Play and Chat's Toolbar overlays all the content on the screen with a transparent background. It uses custom icons, and forgoes the Activity title text.

#### GSON [[link]](https://code.google.com/p/google-gson/)
Since I'm pulling in JSON files and converting them into Objects, GSON was a great choice for JSON to Object conversion.

#### CircleImageView [[link]](https://github.com/hdodenhof/CircleImageView)
Great little library that handles the rather common task of taking a Bitmap, and creating a circle out of it. Play and Chat's user profile image and games images take advantage of this very useful tool.

#### Picasso [[link]](http://square.github.io/picasso/)
Using the power of the legendary artist (maybe?), the Picasso library eases the pain of querying, caching, and resizing images.

For Play and Chat in particular, Picasso assisted in querying for the profile and game images, and storing them in cache.