import { getAllJokes, getJokeById, deleteJoke, updateJoke, createJoke } from '../services/joke'

export default {
  namespace: 'joke',
  state: {
    current: {},
    list: []
  },
  reducers: {
    updateState(state, { newState }) {
      return {
        ...state,
        ...newState
      }
    },

    updateCurrent(state, { current }) {
      return {
        current,
        list: state.list.map(joke => joke.id === current.id ? current : joke)
      }
    }
  },
  effects: {
    *fetchJokeList(_, { call, put }) {
      const list = yield call(getAllJokes)
      yield put({ type: 'updateState', newState: { list } })
    },

    *fetchJoke({ id }, { call, put }) {
      const current = yield call(getJokeById, id)
      yield put({ type: 'updateState', newState: { current } })
    },

    *deleteJoke({ id }, { call, put }) {
      const list = yield call(deleteJoke, id)
      yield put({ type: 'updateState', newState: { list } })
    },

    *updateJoke({ id, body }, { call, put }) {
      const current = yield call(updateJoke, id, body)
      yield put({ type: 'updateCurrent', current })
    },

    *newJoke({ body }, { call, put }) {
      const list = yield call(createJoke, body)
      yield put({ type: 'updateState', newState: { list } })
    }
  }
}
