const {app, BrowserWindow, Tray, nativeImage} = require('electron')

const path = require('path')
const url = require('url')

const server = require('./server/server')

// Keep a global reference of the window object, if you don't, the window will
// be closed automatically when the JavaScript object is garbage collected.
let tray
let mainWindow
let menubarWindow

const createMainWindow = () => {
  mainWindow = new BrowserWindow({
    width: 800,
    height: 600,
    minHeight: 600,
    minWidth: 600
  })

  mainWindow.loadURL(url.format({
    pathname: path.join(__dirname, 'index.html'),
    protocol: 'file:',
    slashes: true
  }))

  mainWindow.on('closed', function () {
    // Dereference the window object, usually you would store windows
    // in an array if your app supports multi windows, this is the time
    // when you should delete the corresponding element.
    mainWindow = null
  })
}

const createMenubarWindow = () => {
  // Make the popup window for the menubar
 menubarWindow = new BrowserWindow({
   width: 300,
   height: 350,
   show: false,
   frame: false,
   resizable: false,
 })

 menubarWindow.loadURL(`file://${path.join(__dirname, 'menubar-index.html')}`)

 menubarWindow.on('blur', () => {
   if(!menubarWindow.webContents.isDevToolsOpened()) {
     menubarWindow.hide()
   }
 })
}

const toggleWindow = (window) => {
  if (window.isVisible()) {
    window.hide()
  } else {
    const trayPos = tray.getBounds()
  const windowPos = window.getBounds()
  let x, y = 0
  if (process.platform == 'darwin') {
    x = Math.round(trayPos.x + (trayPos.width / 2) - (windowPos.width / 2))
    y = Math.round(trayPos.y + trayPos.height)
  } else {
    x = Math.round(trayPos.x + (trayPos.width / 2) - (windowPos.width / 2))
    y = Math.round(trayPos.y + trayPos.height * 10)
  }

  window.setPosition(x, y, false)
  window.show()
  window.focus()
  }
}

const createTrayIcon = () => {
  const icon = nativeImage.createFromPath(path.join(__dirname, 'assets', 'icon.png'))
  tray = new Tray(icon)
  tray.on('click', function(event) {
    toggleWindow(menubarWindow)
    if (menubarWindow.isVisible() && process.defaultApp && event.metaKey) {
      menubarWindow.openDevTools({mode: 'detach'})
    }
  })
}

app.on('ready', () => {
  server.start(9000)
  createMainWindow()
  createMenubarWindow()
  createTrayIcon()
})

// Quit when all windows are closed.
app.on('window-all-closed', function () {
  // On OS X it is common for applications and their menu bar
  // to stay active until the user quits explicitly with Cmd + Q
  if (process.platform !== 'darwin') {
    app.quit()
  }
})

app.on('activate', function () {
  // On OS X it's common to re-create a window in the app when the
  // dock icon is clicked and there are no other windows open.
  if (mainWindow === null) {
    createWindow()
  }
})
