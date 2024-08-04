export function useFetchPostJwt(url = "", accessToken = "", json = {}){
    const response = fetch(url, {
        method: "POST",
        headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + accessToken
        },
        body: JSON.stringify(json)
      })
      return response
}