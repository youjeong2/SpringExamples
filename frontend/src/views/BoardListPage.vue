  <template>
    <div id="board>
    <router-link : to="{ name: 'BoardRegisterPage' }">
    <h2>BoardList</h2>
    </router-link>
    </div>
  </template>

<script>
import BoardList from '@/components/BoardList'

export default {
  name: 'BoardListPage',
  components: {
    BoardList
  }
}
