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
            const isValid = field.validators[name](val)
            errors[name] = not(isValid)
            if (not(isValid)) {
                valid.value = false
            }
        })
    }

    watch(value, checkValid)

    checkValid(value.value)
    return {value, valid, errors, touched, blur: () => touched.value = true}
}