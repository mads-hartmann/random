const {Document} = require('tree-sitter')
const fs = require('fs')
const bash = require('tree-sitter-bash')

// Recursive forEach.
function forEach(node, cb) {
  cb(node)
  if (node.children.length) {
    node.children.forEach(n => forEach(n, cb))
  }
}

const filename = process.argv[2]
const contents = fs.readFileSync(filename, 'utf8');

const document = new Document();
document.setLanguage(bash);
document.setInputString(contents);
document.parse()

console.log('Reading: ', filename)

console.log('Printing Errors')
forEach(document.rootNode, (n) => {
  if (n.hasError()) {
    console.log(`Found error: (${n.startIndex}, ${n.endIndex})`)
  }
})

ns = []

forEach(document.rootNode, (n) => {
  switch (n.type) {
    case 'environment_variable_assignment':
    case 'function_definition':
      ns.push(n)
    default: // pass
  }
});

ns.forEach(n => {
  const start = n.firstNamedChild.startIndex
  const end = n.firstNamedChild.endIndex
  const name = contents.slice(start, end)
  console.log(`Found definition of ${name} at (${start},${end})`);
})
