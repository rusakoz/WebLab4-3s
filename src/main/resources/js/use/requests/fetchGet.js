export function useFetchGet(url = "", headers = {}){
    const res = fetch(url, {
        method: "GET",
        headers
      })
      return res
}