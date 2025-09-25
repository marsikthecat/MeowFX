# MeowFX 🐾

**MeowFX** is a JavaFX mini-framework that makes desktop UIs feel like writing HTML & CSS.  
It wraps JavaFX Nodes into Elements, so you can style, align, and combine components more easily – with a modern, web-inspired API.

---

## ✨ Features

- **HTML-like API**: Elements like `<H1>`, `<P>`, `<A>`, `<Div>`, `<Video>` etc.
- **Responsive Layout**: Bind elements relative to window size (`setResponsiveSize`, `setResponsivePosition`).
- **Styling API**: Apply `backgroundColor`, `border`, `shadow`, hover effects, and more.
- **Positioning Helpers**: Attach elements to each other (`attachXLeftOutsideTo`, `attachYCenter`).
- **Reusable Snippets**: `NavBar`, `SearchBar`, `Accordion`, `HorizontalIconBar`, `VerticalIconBar`.
- **HTML-like Container**: Use `Body` + `Head` + `MeowHTML.show()` to launch your "site".
- **Playful APIs**: Short, fun, and they make you feel strong.

---

## Why MeowFX?

Because my cat named Marsik told me to do this project. 
He said that writing JavaFX code shouldn’t feel like filling out tax forms.
With MeowFX, your desktop UI is more like designing a funky little web page – but you get all the power of Java.

---

## Instalation:
- Just clone the repo
- If it gets more advanced and even stronger: publish on Maven Central
---

## Important:
- MeowFX is in early development; continuous improvements, bug fixes and new features are on the way.


## 🚀 Example

```java
H1 headLine = new H1("Welcome to my Website (It's not actually a website)");
headLine.color(Color.DARKGRAY);
headLine.backgroundColor("lightgrey");

A link1 = new A("Google").href("https://www.google.com");
SearchBar searchBar = new SearchBar();

NavBar navBar = new NavBar();
navBar.backgroundColor("black");
navBar.addMenuItems(link1, searchBar);

Body body = new Body();
body.addAll(headLine, navBar);
body.setResponsiveSize(headLine, 1);

Head head = new Head();
head.setTitle("MeowFX Demo");

MeowHTML app = new MeowHTML(head, body);
app.show();
