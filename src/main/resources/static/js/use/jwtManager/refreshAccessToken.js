import { useFetchPost } from 'use/requests/fetchPost'

export async function useRefreshAccessToken(){
    let isSuccess = false

    const response = await useFetchPost('/user/token', {refreshToken: localStorage.getItem('userRefreshToken')})

    if(response.status === 200){
      const responseJson = response.json()

      let errorText
      await responseJson.then((res)=>{errorText = res.error})

      if(typeof errorText === 'undefined'){
        console.log('все збс')
        responseJson.then((res)=>{
          localStorage.setItem('userAccessToken', res.accessToken)
        })
        isSuccess = true
      }
    }

    return isSuccess
}