import { useDraw } from "use/graph/draw"
import { watch } from "vue"
import { useStore } from "vuex";

export function useWatchRadius(obj){
    const store = useStore()

    useDraw(obj, store)

    watch(()=>obj.radius.value, (val)=>{
        if(parseFloat(val) >= 0){
            useDraw(obj, store)
        }else{
            obj.radius.value = 1
            useDraw(obj, store)
        }
    })
}