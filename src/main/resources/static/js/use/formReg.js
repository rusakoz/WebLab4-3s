import { computed, reactive } from "vue";
import { useField } from "./field";

export function useFormReg(init = {}){
  const form = reactive({})

  for (const [key, value] of Object.entries(init)) {
    form[key] = useField(value)
  }

  form.equalPass = {
        valid: computed(()=>{
            return form.password.value === form.passwordTwo.value
        }) 
    }

  const withoutValid = val => val !== 'valid'
  
  form.valid = computed(()=>{
    console.log('comp')
    return Object.keys(form).filter(withoutValid).reduce((acc, k) =>{
        acc = form[k].valid
        return acc
    }, true)
  })

  return form
}