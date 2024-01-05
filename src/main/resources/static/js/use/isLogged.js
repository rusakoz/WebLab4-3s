import { onMounted, watch } from "vue"

export function useIsLogged(value, itemName, flag){
    onMounted(function(){
      console.log(value, value.value, localStorage.getItem(itemName))
        if(localStorage.getItem(itemName)){
            value.value = localStorage.getItem(itemName) === 'true'
            console.log(value, value.value)
        }
      })
      
      function change(){
        value.value = localStorage.getItem(itemName) === 'true'
      }
      
      watch(flag, change)
}