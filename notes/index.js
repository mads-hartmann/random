var editor = {
    element: document.getElementById('editor'),
};

editor.clear = function() {
    editor.element.innerHTML = '';
}

editor.transform = function(text) {
    var p = document.createElement('p');
    p.textContent = text;
    return [p];
};

editor.update = function() {
    var text = editor.element.textContent;
    editor.clear();
    var nodes = editor.transform(text);
    nodes.forEach(function(n){
        editor.element.appendChild(n);
    });
};

editor.element.addEventListener('keyup', function (event) {
    editor.update();
});
