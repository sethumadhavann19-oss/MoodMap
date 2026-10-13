// No fake live results: until Maps is configured, the UI deliberately shows a setup/demo state.
export const isMapsConfigured = Boolean(import.meta.env.VITE_GOOGLE_MAPS_API_KEY);
export function getMapsStatus() {
  return isMapsConfigured
    ? 'A browser key is present. Load the Maps JavaScript API and connect Places/Routes with key restrictions before showing live results.'
    : 'Demo map only. Add a restricted VITE_GOOGLE_MAPS_API_KEY and integrate Maps JavaScript, Places, and Routes APIs to show verified places and routes.';
}
