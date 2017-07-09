# hatch-reason

You need to have opam, and all sorts of things installed in order for this to
work (at least if you want nice editor support.)

I simply went through the reasonml installation guide.

## Build and deploy

    npm install
    npm run build

Now you have a `lib/js/src/handler.js` file. This is the one we're going 
to deploy. Done.

## TODO:

Everything.

The most interesting problem is figuring out how to build the python Hatch
Service representation based on the reason code. The approach I've started
working towards here is that you need to have a single file that returns a
representation of your service. Hatch could then load the file and simply
inspect that model in order to generate the wanted representation.
