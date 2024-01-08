import { useFetchPost } from 'use/fetchPost'

export async function useRefreshAccessToken(refrehToken){
    let isSuccess = true

    const response = await useFetchPost()

    if(response.status === 200){
      const resJson = response.json()

      let errorText
      await resJson.then((res)=>{errorText = res.error})

      if(typeof errorText !== 'undefined'){
        isSuccess = false
      }else{
        resJson.then((res)=>{
            localStorage.setItem('userAccessToken', res.accessToken)
          })
      }
    }else{
      isSuccess = false
    }

    return isSuccess
}