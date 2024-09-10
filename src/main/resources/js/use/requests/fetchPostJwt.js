export function useFetchPostJwt(url = "", json = {}){
    const response = fetch(url, {
        method: "POST",
        headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('userAccessToken'),
        // 'Clear-Site-Data': '*'
        },
        body: JSON.stringify(json)
      })
      return response
}