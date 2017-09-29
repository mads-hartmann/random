const { app, BrowserWindow, Tray, nativeImage } = require('electron');

const nodemon = require('nodemon');
const path = require('path');
const url = require('url');

// Keep a global reference of the window object, if you don't, the window will
// be closed automatically when the JavaScript object is garbage collected.
let tray;
let mainWindow;
let menubarWindow;

const createMainWindow = () => {
  mainWindow = new BrowserWindow({
    width: 800,
    height: 800,
    minHeight: 600,
    minWidth: 600
  });

  mainWindow.loadURL(
    url.format({
      pathname: path.join(__dirname, 'templates', 'index.html'),
      protocol: 'file:',
      slashes: true
    })
  );

  mainWindow.on('closed', function() {
    // Dereference the window object, usually you would store windows
    // in an array if your app supports multi windows, this is the time
    // when you should delete the corresponding element.
    mainWindow = null;
  });
};

app.on('ready', () => {
  nodemon({
    script: './server/server',
    verbose: true,
    ignore: ['./client', './electron'],
    delay: '2000', // Wait for prettier to finish ;)
    ext: 'js json'
  });
  nodemon
    .once('start', function() {
      // major hack. Waiting for for the node server to be fully started.
      setTimeout(createMainWindow, 500);
    })
    .on('start', function() {})
    .on('quit', function() {
      console.log('Quitting');
      process.exit();
    })
    .on('restart', function(files) {
      console.log('App restarted due to: ', files);
    });
});

// Quit when all windows are closed.
app.on('window-all-closed', function() {
  // On OS X it is common for applications and their menu bar
  // to stay active until the user quits explicitly with Cmd + Q
  if (process.platform !== 'darwin') {
    app.quit();
  }
});

app.on('activate', function() {
  // On OS X it's common to re-create a window in the app when the
  // dock icon is clicked and there are no other windows open.
  if (mainWindow === null) {
    createMainWindow();
  }
});
