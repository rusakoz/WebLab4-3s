<template>
    <div class="main">
        <div class="main-container">
            <div class="container">
                <div class="content">
                    <div class="canvas-and-form">
                        <pre>{{radiusCanvas}}</pre>
                        <div class="canvas">
                            <canvas id="canvas" height="325px" width="325px"></canvas>
                        </div>

                        <div class="form">
                            <h3 class="errMsg" id="errMsg">dasasds</h3>

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
                            </div>
                            
                            <div class="form-control" :class="{invalid: !form.coordY.valid && form.coordY.touched}">
                                <span> Координата Y: </span>

                                <input v-model="form.coordY.value" @blur="form.coordY.blur">
                                <small v-if="(form.coordY.errors.isNum || form.coordY.errors.required) && form.coordY.touched" class="errors">Кордината должна быть числом</small>
                                <small v-else-if="(form.coordY.errors.minVal || form.coordY.errors.maxVal) && form.coordY.touched" class="errors">Допустимый диапозон [-3;3]</small>
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
                                <small v-if="radiusCanvas.radius.errors.minVal && radiusCanvas.radius.touched" class="errors">Радиус не может быть отрицательным</small>
                            </div>
                            <button @click="submit" class="btn-auth" :disabled="!form.valid || !radiusCanvas.valid">Войти</button>
                        </div>

                    </div>
                    <div class="table-wrapper">

                        <div class="table">
                            <div id="result-table">
                                
                            </div>
                        </div>

                        <button value="clear" id="clearButton"></button>

                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useDraw } from 'use/draw'
import { useForm } from 'use/form'

const required = val => !!val
const minVal = num => val => val >= num
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

function submit(){

}

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