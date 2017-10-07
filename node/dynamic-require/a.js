const load = () => {
  return Date.now() % 2 === 0 ? require('./b.js') : require('./c.js');
};

const m = load();
console.log(m.message);
