# Travlr Getaways: CS 465 Final Reflection

Travlr Getaways is a full-stack MEAN application (MongoDB, Express, Angular, Node.js). It has a customer-facing website rendered by Express, an Angular single-page application (SPA) for administrators, and a REST API that both front ends share. Administrators log in with a JSON Web Token (JWT) before adding or editing trips.

## Architecture

**Frontend types compared.** I used three kinds of frontend work in this project.

- **Express with HTML:** The customer site (app_server) renders Handlebars templates on the server. Every click is a new request, and the server returns a complete HTML page. This is simple, requires little client-side code, and is easy for search engines to index. The cost is a full page reload for every interaction.
- **JavaScript:** Plain JavaScript runs on both sides. On the server, it handles routing, controllers, and the API. In the browser, it adds small bits of behavior to the server-rendered pages.
- **Angular SPA:** The admin app (app_admin) loads once and then swaps components in place while it exchanges JSON with the API. It can react to state, such as showing Add Trip and Edit buttons only when a user is logged in, and it validates forms before sending anything. The cost is more structure to learn and a separate build and dev server.

**Why MongoDB.** Trips, rooms, and meals are self-contained records without complicated relationships, so documents fit them well. MongoDB stores data as JSON-like documents, which means data reaches the API and the browser in the same shape without conversion. Mongoose still provides structure through schemas, and the flexible model leaves room to add fields such as bookings and memberships later.

## Functionality

**JSON compared with JavaScript.** JavaScript is a programming language with logic, functions, and behavior. JSON is a text format for data, with only keys and values and no code. JSON ties the layers together because every layer can read and write it. MongoDB stores documents that look like JSON, the Express API sends them with `res.json()`, and Angular receives them through `HttpClient` and binds them straight into templates.

**Refactoring.** Several changes made the code more reliable or easier to maintain:

- I moved all of the Angular HTTP calls into one `TripData` service, so components never build URLs themselves.
- I moved the JWT check into one `authenticateJWT` middleware that protects the write routes instead of checking tokens inside each controller.
- On the Angular side, an HTTP interceptor adds the token to API requests and handles expired tokens in one place, and a route guard protects the Add and Edit screens.
- I corrected the database shutdown code to use async/await, fixed an `instanceof` check in the travel controller, and made the trip lookup return a 404 instead of an empty success.
- I fixed the update endpoint so it returns the updated trip and runs schema validation.

**Reusable UI components.** The `TripCard` component renders one trip, and the listing page repeats it for every trip, so a change to the card changes every card. The navbar, login, and register pages work the same way. On the Express side, the Handlebars header, footer, and layout are shared across every page. Reusable components reduce duplicated code, keep the interface consistent, and make each piece easier to test and change.

## Testing

**Methods, endpoints, and security.** Each endpoint combines an HTTP method with a URL, and the method signals the intent. GET reads trips, POST adds one, and PUT updates one. Testing in Postman means checking more than the successful response. I confirmed the status codes for success, missing data, and unknown trip codes, and I checked that the data changed in MongoDB.

Security added another layer to this. Public GET endpoints can be tested alone, but POST and PUT require a JWT in an `Authorization: Bearer` header. I tested with no token (401), a bad token (403), and a valid token for a trip that did not exist (404), which showed that the token was accepted and the request reached the controller. Testing through the SPA added a second check: the guard, the login state, and the redirect when a token is rejected. The hardest part was telling whether a failure came from the Angular code, the middleware, or the database, so I tested the API alone first and the SPA second.

## Reflection

This course taught me how the pieces of a full-stack application connect: schema, API, security, and interface. I now know how to build and secure a REST API, model data in MongoDB, build components and services in Angular, and protect routes with JWT authentication. The debugging was just as valuable. Tracing dependency injection errors, middleware ordering problems, and change detection bugs taught me to isolate each layer before guessing. These skills make me a stronger candidate for full-stack and web development roles because I can show a working application with both a customer side and a secured administrator side. I am unsure what the future holds, but I have no doubt the skills I learned throughout this course will serve me well. I am already on a career path tangential to my original direction in Computer Science, but I hope I can transition or find a way to apply my skills to my current career path. 
