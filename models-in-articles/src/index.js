const CLICKED_CLASS = 'variable--active';
const ATTR_INITIAL_X = 'data-initial-x';
const ATTR_MIN = 'data-min';
const ATTR_MAX = 'data-max';
const ATTR_STEP = 'data-step';

function mousedown(e) {
    e.target.classList.toggle(CLICKED_CLASS);
    e.target.setAttribute(ATTR_INITIAL_X, e.clientX);
    e.preventDefault();
}
function mouseup(e) {
    e.target.classList.remove(CLICKED_CLASS);
    e.target.removeAttribute(ATTR_INITIAL_X);
    e.preventDefault();
}

function mousemove(e) {
    if (e.target.classList.contains(CLICKED_CLASS)) {
        const max = parseFloat(e.target.getAttribute(ATTR_MAX));
        const min = parseFloat(e.target.getAttribute(ATTR_MIN));
        const step = parseFloat(e.target.getAttribute(ATTR_STEP));
        const width = e.target.offsetWidth;

        const numberOfSteps = (max - min) / step;
        const pixelsPerStep = Math.max(1, Math.floor(width / numberOfSteps));

        const valuePrPixel = (max - min) / (width - 10); // 10 = padding to make for a better UX

        // const newValue = ((e.clientX - e.target.offsetLeft) / pixelsPerStep * step) + min;
        const newValue = (e.clientX - e.target.offsetLeft) * valuePrPixel;

        const capped = Math.max(min, Math.min(max, newValue));

        e.target.getElementsByTagName('span')[0].innerText = capped.toFixed(1);
    }
    e.preventDefault();
}

function onready(){
    const variables = document.getElementsByClassName('variable');
    Array.prototype.forEach.call(variables, (variable) => {
        variable.addEventListener('mousedown', mousedown);
        variable.addEventListener('mousemove', mousemove);
        variable.addEventListener('mouseup', mouseup);
        variable.addEventListener('mouseleave', mouseup);
    });
}

document.addEventListener('DOMContentLoaded', onready, false);
