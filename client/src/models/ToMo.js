import request from '../utils/request'

export default{
    namespace: 'text',
    state: {
        name: 1,
    },
    reducers: {
        update(state,{payload}){
            return {
                ...state,
                name: payload
            }
        }
    },
    effects: {
        *fetchText(action,saga){
            const {call,put} = saga
            // debugger
            const response = yield call(request,'/api/text')

            yield put({
                type: 'update',
                payload: response.name
            })
        }
    },
    subscriptions: {

    }
}
