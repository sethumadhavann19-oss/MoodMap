export default function MoodJourney({from,to}) {
 return <section className="journey panel" aria-label="Mood journey">
  <div className="journey-head"><div><span className="eyebrow">YOUR MOOD JOURNEY</span><h3>One small step at a time</h3></div><span className="journey-icon">✦</span></div>
  <div className="journey-track"><div className="journey-point"><span>{from?.emoji||'◌'}</span><small>Right now</small><b>{from?.name||'Not selected'}</b></div><div className="journey-path"><i>→</i></div><div className="journey-point target"><span>{to?.emoji||'☼'}</span><small>Your direction</small><b>{to?.name||'Optional'}</b></div></div>
  <p className="muted tiny">No mood is wrong. Your destination mood is a direction, not a requirement.</p>
 </section>
}
