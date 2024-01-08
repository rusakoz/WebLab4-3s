<template>
    <div class="main">
        <div class="main-container">
            <div class="container">
                <div class="content">
                    <div class="canvas-and-form">
                        <!-- <pre>{{form}}</pre> -->
                        <div class="canvas">
                            <canvas id="canvas" height="325px" width="325px"></canvas>
                        </div>

                        <div class="form">

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
                            <button @click="submit" class="btn-auth" :disabled="!form.valid || !radiusCanvas.valid">Войти</button>
                        </div>
                    </div>
                    <div class="table-wrapper">

                        <div class="table">
                            <div id="div-result-table">
                                <table id="result-table">
                                    <thead>
                                        <tr>
                                            <td>X</td>
                                            <td>Y</td>
                                            <td>R</td>
                                            <td>Hit</td>
                                            <td>Date</td>
                                            <td>ExecTime</td>
                                        </tr>
                                    </thead>
                                    <small v-if="loadErr" class="errors" id="errMsg">{{ loadErrText }}</small>
                                    <tbody>
                                        <tr v-for="(iterObject) in tableData">
                                            <td>{{iterObject.x}}</td>
                                            <td>{{iterObject.y}}</td>
                                            <td>{{iterObject.r}}</td>
                                            <td>{{iterObject.hit}}</td>
                                            <td>{{iterObject.date}}</td>
                                            <td>{{iterObject.execTime}}</td>
                                        </tr>
                                    </tbody>
                                </table>
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
import { useDraw } from 'use/draw'
import { useForm } from 'use/form'
import { useGetTableData } from 'use/getTableData'
import { useRefreshAccessToken } from 'use/refreshAccessToken'

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

const tableData = reactive([{
    x: '',
    y: '',
    r: '',
    hit: '',
    date: '',
    execTime: ''
}])

const loadErrText = ref('')
const loadErr = ref(false)

async function loadTable(){
    function fetch(){
        return useGetTableData('/result/gett', 
                        {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json',
                            'Authorization': 'Bearer ' + localStorage.getItem('userAccessToken')
                        })
                        .catch(()=>{
                            loadErrText.value = 'Ошибка загрузки данных'
                            loadErr.value = true
                        })
    }

    const response = await fetch()
    
    if(response.status === 401){
        if(useRefreshAccessToken(localStorage.getItem('userRefreshToken'))){
            const response = await fetch()
                    .catch(()=>{
                            loadErrText.value = 'Ошибка загрузки данных'
                            loadErr.value = true
                            })
            if(response.status === 200){
                fillTable(response)
            }else{
                loadErrText.value = 'Ошибка загрузки данных'
                loadErr.value = true
            }
        }else{
            localStorage.removeItem('userAccessToken', res.accessToken)
            localStorage.removeItem('userRefreshToken', res.refreshToken)
            localStorage.setItem('isLoggin', false)
            alert('Время авторизации вышло, авторизуйтесь заново')
        }
    }
    else if(response.status === 200){
        fillTable(response)
    }
    else{
        loadErrText.value = 'Ошибка загрузки данных'
        loadErr.value = true
    }

    function fillTable(response){
        const data = response.json()

        data.then((a)=>{
            for (const [key, value] of Object.entries(a)) {
                const date = new Date(Date.parse(value.date))
                tableData.push({
                                x: value.x,
                                y: value.y,
                                r: value.r,
                                hit: value.hit,
                                date: date.toLocaleDateString() + ' ' + date.toLocaleTimeString(),
                                execTime: value.execTime
                            })
                
                console.log(key, value, tableData, tableData.length)
            }
            if(tableData.length >= 1) tableData.pop
        })
    }
}

loadTable()



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

</style>