# play-heroku-s3-uploader
This is an example [Play 2.1.x][play] application of how to use [play-s3][play-s3] on [heroku][heroku].

# Configuration
Add your s3 bucket name, access key, and secret key to your heroku repository:

    heroku config:set AWS_S3_BUCKET=bucket_name AWS_ACCESS_KEY=access_key AWS_SECRET_KEY=secret_key

Optionally you can just supply your access and secret key and not use the s3 bucket configuration key. Then in your application set your bucket name manually:

    val bucketName = Play.application.configuration.getString("my.s3bucket").get

to:

    val bucketName = "yourbucket.com"

# Usage
Clone repository then create a heroku stack, set the configuration keys, push repo to heroku.

## License
MIT: <http://jmparsons.mit-license.org> - [@jmparsons](http://twitter.com/jmparsons)

[heroku]: https://www.heroku.com/
[play-s3]: https://github.com/Rhinofly/play-s3
[play]: http://www.playframework.org/