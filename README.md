# MoodMap — Emotion-Aware Route Planner

A full-stack app that picks walking routes based on how you want to *feel*, not just
the shortest path. Built with Spring Boot + H2 on the backend and React (Vite) on the
frontend.

## How it works (for your resume / interview talking points)

1. **Graph model** — A small sample town is modeled as a graph: `GraphNode` (places)
   and `GraphEdge` (street segments). Each edge carries physical distance plus four
   environmental attributes (0.0–1.0): greenery, noise, crowd density, vibrancy.

2. **Mood-weighted Dijkstra** — `DijkstraService` runs textbook Dijkstra (priority
   queue + relaxation), but the edge "weight" isn't distance — it's an *emotional
   cost* from `MoodCostCalculator`. Each target mood (CALM, FOCUSED, ENERGIZED, HAPPY)
   has an ideal environmental profile; an edge's cost = distance × (1 + mismatch
   penalty), so a mismatched edge can cost several times its physical length and gets
   routed around even if it's shorter.

3. **Personalization loop** — `UserPreference` (persisted in H2) stores per-user
   sensitivity multipliers for each attribute. After a walk, the user rates how it
   actually felt (-2 to +2). `FeedbackService` nudges those multipliers up or down —
   a lightweight, explainable online-learning rule rather than a black-box model.

4. **REST API** — three endpoints: `GET /api/graph` (map data), `POST /api/route`
   (compute a route for a mood pair), `POST /api/feedback` (submit a rating and
   update preferences).

5. **Frontend** — React renders the town as an SVG map. The recommended route is
   drawn as a "mood thread": each segment's color blends from the target mood's
   accent color (great fit) toward a muted red (poor fit), so you can see *why* the
   algorithm chose that path, not just where it goes.

## Run it locally (Windows, JDK 25 + Node.js already installed)

**Backend**

Easiest: open the `backend` folder in IntelliJ IDEA (Community is fine) and run
`MoodMapApplication.java` directly — it'll auto-detect the Maven project and download
dependencies the first time.

Or from the command line, if Maven is installed:
```
cd moodmap/backend
mvn spring-boot:run
```
It starts on `http://localhost:8080`. The H2 console is at `http://localhost:8080/h2-console`
(JDBC URL: `jdbc:h2:mem:moodmap`, user `sa`, no password).

**Frontend** (separate terminal)
```
cd moodmap/frontend
npm install
npm run dev
```
Opens on `http://localhost:5173`.

## Project structure
```
backend/
  src/main/java/com/moodmap/
    model/       GraphNode, GraphEdge, Mood, UserPreference
    service/     GraphService (the map), MoodCostCalculator, DijkstraService, FeedbackService
    controller/  GraphController, RouteController, FeedbackController
    dto/         request/response records
frontend/
  src/
    components/  MoodPicker, MapView, RouteSummary, FeedbackPanel
    App.jsx, api.js, styles.css
```

## Talking points if asked in an interview
- Why Dijkstra and not A*: the graph is small and we need the true shortest emotional
  cost to every node reachable from the start, not just one destination heuristic —
  though A* would be a fair "how would you scale this" follow-up answer.
- Why H2 in-memory instead of MySQL: zero external setup, resets cleanly each run —
  good for a demo; swapping to MySQL/Postgres only needs a datasource config change
  since it's plain Spring Data JPA.
- The learning loop is intentionally simple (rule-based nudges, not ML) so it's fully
  explainable — a natural place to mention Ridge regression or another model as a
  "next step" if you want to bring up your ML interest.
