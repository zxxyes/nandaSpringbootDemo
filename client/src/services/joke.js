import request from '../utils/request'

export async function getAllJokes() {
  return request.get('/api/jokes')
}

export async function getJokeById(id) {
  return request.get(`/api/jokes/${id}`)
}

export async function createJoke(joke) {
  return request.post('/api/jokes/', { headers: { "Content-Type": "application/json;charset:UTF-8"}, body: JSON.stringify(joke) })
}

export async function updateJoke(id, joke) {
  return request.put(`/api/jokes/${id}`, { headers: { "Content-Type": "application/json" }, body: JSON.stringify(joke) })
}

export async function deleteJoke(id) {
  return request.delete(`/api/jokes/${id}`)
}
