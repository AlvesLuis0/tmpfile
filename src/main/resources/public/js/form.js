(function() {

const form = document.forms[0]
const input = document.getElementById('file')
const outputBox = document.getElementById('output-box')
const outputLink = document.getElementById('output-link')
const copyBtn = document.getElementById('copy-btn')

function getFile() {
  return input.files[0]
}

async function uploadFile(file) {
  const data = new FormData()
  data.append('file', file)
  const path = '/upload'
  const request = {
    method: 'POST',
    body: data
  }
  return fetch(path, request)
    .then(response => response.json())
}

function shareLink(link) {
  link = link.substring(1)
  outputLink.href = link
  const fullLink = location.href + link
  outputLink.innerText = fullLink
  outputBox.hidden = false
  copyBtn.onclick = () => {
    navigator.clipboard.writeText(fullLink)
    alert('Copied!')
  }
}

form.addEventListener('submit', (event) => {
  event.preventDefault()
  const file = getFile()
  uploadFile(file)
    .then(json => shareLink(json.url))
  form.reset()
})

})()