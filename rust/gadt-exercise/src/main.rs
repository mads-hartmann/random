// The first goal is to implement the ADT version from https://blog.mads-hartmann.com/ocaml/2015/01/05/gadt-ocaml.html
// See https://blog.softwaremill.com/algebraic-data-types-in-four-languages-858788043d4e for how to model ADTs in Rust

#[derive(Debug)]
enum Value {
    Bool(bool),
    Int(i64),
    Failure,
}

#[derive(Debug)]
enum Expression {
    Value(Value),
    If(Box<Expression>, Box<Expression>, Box<Expression>),
    Eq(Box<Expression>, Box<Expression>),
    Lt(Box<Expression>, Box<Expression>),
}

fn eval(expr: Expression) -> Value {
    match expr {
        Expression::Value(value) => value,
        Expression::Lt(x, y) => match (eval(*x), eval(*y)) {
            (Value::Int(x), Value::Int(y)) => Value::Bool(x < y),
            (Value::Int(_), Value::Bool(_)) => Value::Failure,
            (Value::Bool(_), Value::Int(_)) => Value::Failure,
            (Value::Bool(_), Value::Bool(_)) => Value::Failure,
            (_, _) => Value::Failure,
        },
        Expression::If(b, l, r) => match eval(*b) {
            Value::Bool(true) => eval(*l),
            Value::Bool(false) => eval(*r),
            _ => Value::Failure,
        },
        Expression::Eq(a, b) => match (eval(*a), eval(*b)) {
            (Value::Int(x), Value::Int(y)) => Value::Bool(x == y),
            (Value::Bool(x), Value::Bool(y)) => Value::Bool(x == y),
            (_, _) => Value::Failure,
        },
    }
}

fn main() {
    let value = eval(Expression::If(
        Box::new(Expression::Lt(
            Box::new(Expression::Value(Value::Int(2))),
            Box::new(Expression::Value(Value::Int(4))),
        )),
        Box::new(Expression::Value(Value::Int(42))),
        Box::new(Expression::Value(Value::Int(0))),
    ));

    println!("hello world");
    println!("{:?}", value);
}
