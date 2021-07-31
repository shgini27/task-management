import axios from 'axios'
import errorParser from '@/utils/error-parser'

export default {
  /**
   * Method to add new card
   * @param detail
   * @returns {Promise<unknown>}
   */
  add (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/cards', detail).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },

  /**
   * Method to change positions of a cards
   * @param positionChanges
   * @returns {Promise<unknown>}
   */
  changePosition (positionChanges) {
    return new Promise((resolve, reject) => {
      axios.post('/cards/positions', positionChanges).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
