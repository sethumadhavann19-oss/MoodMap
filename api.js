const API_BASE = import.meta.env.VITE_API_BASE_URL || '';
// Explicit future integration seam. These calls are not used by demo mode until a backend is available.
async function request(path, options = {}) {
  if (!API_BASE) throw new Error('Backend is not configured. Continue in demo mode.');
  const response = await fetch(`${API_BASE}${path}`, { headers: { 'Content-Type': 'application/json', ...options.headers }, ...options });
  if (!response.ok) throw new Error(`Request failed (${response.status}). Please try again.`);
  return response.status === 204 ? null : response.json();
}
export const api = {
  getProfile: () => request('/profile'),
  saveFeedback: (payload) => request('/mood/feedback', { method: 'POST', body: JSON.stringify(payload) })
};
export const isBackendConfigured = Boolean(API_BASE);
