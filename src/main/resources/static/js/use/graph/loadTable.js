import { useFetchGet } from 'use/requests/fetchGet'
import { useRefreshAccessToken } from 'use/jwtManager/refreshAccessToken'
import { useForcedLogout } from 'use/router/forcedLogout'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

const errObj = {
    loadErrText: 'Ошибка загрузки данных',
    loadErr: true
}

function request(){
    return useFetchGet('/result/get', 
                    {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + localStorage.getItem('userAccessToken')
                    })
}

async function fillData(responseJson, tableData){
    let result = []

    await responseJson.then((a)=>{
        for (const [key, value] of Object.entries(a)) {
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
        if(tableData.length >= 1) tableData.shift()
        result = tableData
    })

    return result
}

export async function useLoadTable(init = {}){
    const router = useRouter()
    const store = useStore()

    const tableData = init

    const response = await request().catch(()=>{tableData.error = errObj})

    if(response.status === 401){

        let isRefresh = false
        await useRefreshAccessToken().then((status)=>{isRefresh = status})

        if(isRefresh){
            const response = await request().catch(()=>{tableData.error = errObj})
            if(response.status === 200){
                const responseJson = response.json()

                let errText
                await responseJson.then((data)=>{errText = data.error})

                if(typeof errText === 'undefined'){
                    fillData(responseJson, tableData.data).then((data)=>{tableData.data = data})
                }else{
                    useForcedLogout(store, router)
                }

            }else if(response.status === 401){
                useForcedLogout(store, router)
            }
            else{
                tableData.error = errObj
            }
        }else{
            useForcedLogout(store, router)
        }
    }
    else if(response.status === 200){
        const data = response.json()
        fillData(data, tableData.data).then((data)=>{tableData.data = data})
    }
    else{
        tableData.error = errObj
    }

    tableData.status.now = ''
    return tableData
}