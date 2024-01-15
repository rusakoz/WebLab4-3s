<template>
    <div class="main">
        <div class="main-container">
            <div class="container">
                <div class="content">
                    <div class="canvas-and-form">
                        <div class="canvas">
                            <canvas @click="cl" id="canvas" height="325px" width="325px"></canvas>
                        </div>

                        <div class="form">
                            <small v-if="submitInfo.status === 'err'" class="errors">{{ submitInfo.text }}</small>
                            <small v-else-if="submitInfo.status === 'sending'" class="errors">{{ submitInfo.text }}</small>
                            <div class="form-control" :class="{invalid: !form.coordX.valid && form.coordX.touched}">
                                <span> Координата X: </span>

                                <select v-model="form.coordX.value" @blur="form.coordX.blur">
                                    <option disabled value="">Выберите координату X</option>
                                    <option>-3</option>
                                    <option>-2</option>
                                    <option>-1</option>
                                    <option>0</option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                </select>
                                <small v-if="form.coordX.errors.required && form.coordX.touched" class="errors">
                                    Выберите координату
                                </small>
                            </div>
                            
                            <div class="form-control" :class="{invalid: !form.coordY.valid && form.coordY.touched}">
                                <span> Координата Y: </span>

                                <input v-model="form.coordY.value" @blur="form.coordY.blur">

                                <small v-if="(form.coordY.errors.isNum || form.coordY.errors.required) && form.coordY.touched" class="errors">
                                    Кордината должна быть числом
                                </small>
                                <small v-else-if="(form.coordY.errors.minVal || form.coordY.errors.maxVal) && form.coordY.touched" class="errors">
                                    Допустимый диапозон [-3;3]
                                </small>
                            </div>

                            <div class="form-control" :class="{invalid: !radiusCanvas.radius.valid && radiusCanvas.radius.touched}">
                                <span> Радиус: </span>

                                <select v-model="radiusCanvas.radius.value" @blur="radiusCanvas.radius.blur">
                                    <option disabled value="">Выберите радиус</option>
                                    <option>-3</option>
                                    <option>-2</option>
                                    <option>-1</option>
                                    <option>0</option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                </select>
                                <small v-if="radiusCanvas.radius.errors.minVal && radiusCanvas.radius.touched" class="errors">
                                    Радиус не может быть отрицательным
                                </small>
                            </div>
                            <button @click="submitButton" class="btn-auth" :disabled="!form.valid || !radiusCanvas.valid">Отправить</button>
                        </div>
                    </div>
                    <div class="table-wrapper">

                        <div class="table">
                            <div id="div-result-table">
                                <table id="result-table">
                                    <thead>
                                        <tr>
                                            <th>X</th>
                                            <th>Y</th>
                                            <th>R</th>
                                            <th>Hit</th>
                                            <th>Date</th>
                                            <th>ExecTime</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr v-for="(iterObject) in tableData.data">
                                            <td>{{iterObject.x}}</td>
                                            <td>{{iterObject.y}}</td>
                                            <td>{{iterObject.r}}</td>
                                            <td>{{iterObject.hit}}</td>
                                            <td>{{iterObject.date}}</td>
                                            <td>{{iterObject.execTime}}</td>
                                        </tr>
                                    </tbody>
                                </table>
                                <small v-if="tableData.error.loadErr" class="errors" id="errMsg">{{ tableData.error.loadErrText }}</small>
                                <small v-else-if="tableData.status.now">{{ tableData.status.now }}</small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { onMounted, reactive, ref } from 'vue'
import { useDraw } from 'use/graph/draw'
import { useForm } from 'use/validForm/form'
import { useLoadTable } from 'use/graph/loadTable'
import { useFetchPostJwt } from 'use/requests/fetchPostJwt'
import { useRefreshAccessToken } from 'use/jwtManager/refreshAccessToken'
import { useForcedLogout } from 'use/router/forcedLogout'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { useConvertCoord } from 'use/graph/convertCoord'

function cl(event){
    const coords = useConvertCoord(event, store)
    request(coords.x, coords.y, radiusCanvas.radius.value)
}

const router = useRouter()
const store = useStore()

const required = val => !!val
const minVal = num => val => val >= num // minVal(3)(2) minVal(3)(4)
const maxVal = num => val => val <= num
const isNum = val => !isNaN(val)

const form = useForm({
    coordX: {
        value: '',
        validators: {required}
    },
    coordY: {
        value: '',
        validators: {required, minVal: minVal(-3), maxVal: maxVal(3), isNum}
    }
})

const radiusCanvas = useForm({
    radius: {
        value: 1,
        validators: {minVal: minVal(0)}
    },
    canvas: {
        obj: 1
    }
})

const tableData = reactive({
    data: [{
    x: '',
    y: '',
    r: '',
    hit: '',
    date: '',
    execTime: ''
    }],
    status: {
        now: 'loading...'
    },
    error: {
        loadErrText: '',
        loadErr: false
    }
})

const submitInfo = reactive({
    status: '',
    text: ''
})

function submitButton(){
    request(form.coordX.value, form.coordY.value, radiusCanvas.radius.value)
}

async function request(x, y, r){
    submitInfo.status = 'sending'
    submitInfo.text = 'Отправка данных...'
    function request(){
        return useFetchPostJwt('/result/save', {
                                                x: x,
                                                y: y,
                                                r: r,
                                                })
    }

    const response = await request().catch(()=>{ setErr() })

    if(response.status === 201){
        const data = response.json()

         data.then((data)=>{
            const date = new Date(Date.parse(data.date))
            tableData.data.push({
            x: data.x,
            y: data.y,
            r: data.r,
            hit: data.hit ? 'Попал' : 'Не попал',
            date: date.toLocaleDateString() + ' ' + date.toLocaleTimeString(),
            execTime: data.execTime + 'мс'
            })
        }) 

    }else if(response.status === 400){
        const data = response.json()

        data.then((error)=>{errSubmit.status = true; errSubmit.text = error.error})
    }else if(response.status === 401){
        let isRefresh = false
        await useRefreshAccessToken().then((status)=>{isRefresh = status})

        if(isRefresh){
            const response = await request().catch(()=>{ setErr() })

            if(response.status === 201){
                const data = response.json()
                
                let errText
                await data.then((data)=>{errSubmit.status = true
                                        errSubmit.text = data.error
                                        })

                if(typeof errText === 'undefined'){
                    submitInfo.status = ''
                    submitInfo.text = ''

                    data.then((data)=>{
                    const date = new Date(Date.parse(data.date))
                    tableData.data.push({
                    x: data.x,
                    y: data.y,
                    r: data.r,
                    hit: data.hit ? 'Попал' : 'Не попал',
                    date: date.toLocaleDateString() + ' ' + date.toLocaleTimeString(),
                    execTime: data.execTime + 'мс'
                    })
                }) 
                }else{
                    useForcedLogout(store, router)
                }
            }else if(response.status === 401){
                useForcedLogout(store, router)
            }
            else{
                setErr()
            }
        }else{
            useForcedLogout(store, router)
        }
    }
    else{
        setErr()
    }

    function setErr(){
        submitInfo.status = 'err'
        submitInfo.text = 'Ошибка отправки данных'
    }
}

useLoadTable(tableData).then((res)=>{tableData.value = res})

onMounted(function(){
    radiusCanvas.canvas.obj = document.getElementById('canvas')
    useDraw(radiusCanvas)
})

</script>

<style scoped>

.form-control.invalid select{
    border: 2px solid;
    border-color: rgb(216, 0, 0);
  }

  .errors{
    color: rgb(167, 27, 27);
    font-size: 13px;
    margin-left: 3px;
  }

  .btn-auth:disabled{
    cursor: default;
    color: #212529;
    background-color: #848688;
  }

  table {
    width: 100%;
  text-align: left;
  border-collapse: collapse;
  }

  thead th {
  font-size: 15px;
  font-weight: bold;
  color: #2e2b2b;
  border-left: 2px solid #D0E4F5;
}

 td, th {
  border: 1px solid #AAAAAA;
  padding: 3px 2px;
}

</style>