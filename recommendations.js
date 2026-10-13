export const currentMoods = [
  { name: 'Stressed', emoji: '😣', note: 'A lot on your mind' }, { name: 'Sad', emoji: '🌧️', note: 'A softer moment' },
  { name: 'Bored', emoji: '🥱', note: 'Ready for a change' }, { name: 'Tired', emoji: '😴', note: 'Low on energy' },
  { name: 'Anxious', emoji: '🫧', note: 'Looking for steadiness' }, { name: 'Frustrated', emoji: '😤', note: 'Need a reset' },
  { name: 'Confused', emoji: '🌀', note: 'Need some clarity' }, { name: 'Lonely', emoji: '🫂', note: 'Could use connection' }
];
export const desiredMoods = [
  { name: 'Peaceful', emoji: '🌿' }, { name: 'Focused', emoji: '🎯' }, { name: 'Motivated', emoji: '🔥' },
  { name: 'Confident', emoji: '✨' }, { name: 'Refreshed', emoji: '💧' }, { name: 'Relaxed', emoji: '☁️' }
];
export const activities = [
 {id:'walk',name:'A little nature walk',emoji:'🚶',category:'Outdoors',minutes:20,kind:'place',targets:['Peaceful','Relaxed','Refreshed'],helps:['Stressed','Anxious','Sad','Frustrated'],description:'Easy steps, fresh air, and no pressure to get anywhere.',why:'Gentle movement and a change of scenery may help you reset.'},
 {id:'ride',name:'An easy ride',emoji:'🚲',category:'Outdoors',minutes:25,kind:'place',targets:['Motivated','Refreshed','Confident'],helps:['Bored','Tired','Sad'],description:'Move at your own pace and let the scenery change.',why:'A little movement can create momentum without a big commitment.'},
 {id:'short-drive',name:'Short scenic drive',emoji:'🚗',category:'Outdoors',minutes:25,kind:'place',targets:['Refreshed','Focused','Confident'],helps:['Bored','Frustrated','Confused'],description:'A short change of scene with a playlist you love.',why:'A new setting can offer a useful mental break. Drive only when safe.'},
 {id:'long-drive',name:'Long drive plan',emoji:'🛣️',category:'Outdoors',minutes:60,kind:'place',targets:['Peaceful','Motivated'],helps:['Stressed','Lonely','Bored'],description:'Plan a longer route for a day when you have time.',why:'A slower outing can give you space. Never drive when tired or distressed.'},
 {id:'music',name:'Music for this moment',emoji:'🎧',category:'At home',minutes:10,kind:'home',targets:['Peaceful','Relaxed','Refreshed','Confident'],helps:['Sad','Stressed','Lonely','Bored'],description:'Choose a playlist that matches how you want to feel.',why:'Music is a personal way to pause and shift the atmosphere.'},
 {id:'rest',name:'A proper rest',emoji:'🌙',category:'At home',minutes:20,kind:'home',targets:['Refreshed','Relaxed','Peaceful'],helps:['Tired','Stressed','Anxious'],description:'Put your phone aside and give yourself a quiet break.',why:'Rest may be the most supportive next step when energy is low.'},
 {id:'movie',name:'Favourite movie moment',emoji:'🎬',category:'At home',minutes:30,kind:'home',targets:['Peaceful','Relaxed'],helps:['Sad','Lonely','Bored','Tired'],description:'Revisit a comforting scene or start a favourite film.',why:'A familiar story can feel comforting and easy to begin.'},
 {id:'games',name:'Play something fun',emoji:'🎮',category:'At home',minutes:15,kind:'home',targets:['Focused','Motivated','Confident'],helps:['Bored','Frustrated'],description:'Pick a game you enjoy, not one that adds pressure.',why:'A small, enjoyable challenge can help you regain a sense of agency.'},
 {id:'breathing',name:'Guided breathing pause',emoji:'🧘',category:'At home',minutes:5,kind:'home',targets:['Peaceful','Focused','Relaxed'],helps:['Anxious','Stressed','Confused'],description:'Try a gentle five-minute breathing break; stop if uncomfortable.',why:'A brief pause can help you notice the present moment.'},
 {id:'hobby',name:'Your favourite hobby',emoji:'🎨',category:'At home',minutes:20,kind:'home',targets:['Motivated','Focused','Confident'],helps:['Bored','Lonely','Frustrated'],description:'Make, cook, read, practise, or do something that feels yours.',why:'A familiar interest can offer enjoyment and a small sense of progress.'}
];
export function getRecommendations(from,to,category='All') {
 return activities.filter(a => category==='All'||a.category===category)
 .map(a=>({...a,score:(to&&a.targets.includes(to)?3:0)+(from&&a.helps.includes(from)?2:0)}))
 .sort((a,b)=>b.score-a.score);
}
