import {ref} from "vue";

export function submit(){
  const auth = ref(false)
  function submit(){
    auth.value = !auth.value
  }

  return auth
}
