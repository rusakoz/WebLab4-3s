import { reactive, ref, watch } from "vue"

const not = val => !val

export function useField(field){
    const valid = ref(true)
    const value = ref(field.value)
    const errors = reactive({})
    const touched = ref(false)
    
    const checkValid = val =>{
        valid.value = true
        Object.keys(field.validators ?? {}).map(name =>{
            console.log(value.value, 'field value')
            console.log(name, 'name')
            console.log(val, 'val')
            const isValid = field.validators[name](val)
            errors[name] = not(isValid)
            console.log(isValid)
            if (not(isValid)) {
                valid.value = false
            }
        })
    }

    watch(value, checkValid)

    checkValid(value.value)
    return {value, valid, errors, touched, blur: () => touched.value = true}
}