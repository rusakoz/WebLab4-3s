import { useFetchGet } from 'use/requests/fetchGet'
import { useRefreshAccessToken } from 'use/jwtManager/refreshAccessToken'
import { useForcedLogout } from 'use/router/forcedLogout'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

const errObj = {
    loadErrText: 'Ошибка загрузки данных',
    loadErr: true
}

function request() {
    return useFetchGet('/result/get',
        {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('userAccessToken')
        })
}

async function fillData(array, tableData) {
    let result = []

    for (const [key, value] of Object.entries(array)) {
        const date = new Date(Date.parse(value.date))
        tableData.push({
            x: value.x,
            y: value.y,
            r: value.r,
            hit: value.hit ? 'Попал' : 'Не попал',
            date: date.toLocaleDateString() + ' ' + date.toLocaleTimeString(),
            execTime: value.execTime + 'мс'
        })
    }
    if (tableData.length >= 1) tableData.shift()
    result = tableData


    return result
}

export async function useLoadTable(init = {}) {
    const UNAUTHORIZED_CODE = 401
    const OK_CODE = 200

    const router = useRouter()
    const store = useStore()

    const tableData = init

    const response = await request().catch(() => { tableData.error = errObj })
    const { status, error } = response

    if (status === OK_CODE) {
        const data = await response.json()
        fillData(data, tableData.data)
    }
    else if (status === UNAUTHORIZED_CODE) {

        let successRefreshToken = false
        await useRefreshAccessToken().then((status) => { successRefreshToken = status }).catch(() => { tableData.error = errObj })

        if (!successRefreshToken) {
            useForcedLogout(store, router)
        }

        const response = await request().catch(() => { tableData.error = errObj })
        const { status, error } = response

        if (status === OK_CODE) {
            const responseJson = await response.json()
            const hasError = Boolean(responseJson.error)

            if (hasError) {
                useForcedLogout(store, router)
            }

            fillData(responseJson, tableData.data)

        }
        else if (status === UNAUTHORIZED_CODE) {
            useForcedLogout(store, router)
        }
        else {
            tableData.error = errObj
        }

    }
    else {
        tableData.error = errObj
    }

    tableData.status.now = ''
    return tableData
}